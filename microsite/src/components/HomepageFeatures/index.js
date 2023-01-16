import React from 'react';
import clsx from 'clsx';
import styles from './styles.module.css';

const FeatureList = [
  {
    title: 'Easy to Use',
    Svg: require('@site/static/img/easy.svg').default,
    description: (
      <>
        The bifrost-daml-broker was designed from the ground up to be easily installed and
        used to get your DAML app quickly integrated to the Topl Blockchain.
      </>
    ),
  },
  {
    title: 'Smart Contract First',
    Svg: require('@site/static/img/smart.svg').default,
    description: (
      <>
        DAML lets you focus on your business logic and workflows. The integration with Bifrost node is handled by the broker.
      </>
    ),
  },
  {
    title: 'Powered by the Blockchain',
    Svg: require('@site/static/img/topl_blockchain.svg').default,
    description: (
      <>
        Bifrost is a blockchain that is built to be fast, secure, and scalable.
      </>
    ),
  },
];

function Feature({Svg, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} role="img" />
      </div>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
